package zhku.graduation.core.modules.status.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhku.graduation.basic.controller.BaseController;
import zhku.graduation.basic.vo.Result;
import zhku.graduation.core.modules.status.entity.bean.TemperatureLimitDetail;
import zhku.graduation.core.modules.status.entity.bean.TemperatureLimitListInfo;
import zhku.graduation.core.modules.status.entity.bean.TemperatureLimitListRequest;
import zhku.graduation.core.modules.status.service.ITemperatureLimitService;

import java.util.List;

import static zhku.graduation.basic.constant.HttpStatus.ERROR;
import static zhku.graduation.basic.constant.HttpStatus.PARAM_MISSING;

/**
 * @author QR
 * @since 2022-04-04
 */
@Api(tags = "鱼缸温度限制")
@RestController
@RequestMapping("/limit/")
public class TemperatureLimitController extends BaseController {

    @Autowired
    private ITemperatureLimitService temperatureLimitService;

    @ApiOperation(value = "查询鱼缸温度限制详情", response = TemperatureLimitDetail.class)
    @GetMapping("get/{nodeId}")
    public Result<?> getTemperatureLimit(@PathVariable(value = "nodeId") Integer nodeId){
        if (nodeId == null) {
            return error(PARAM_MISSING);
        }
        TemperatureLimitDetail info = temperatureLimitService.getTemperatureLimit(nodeId);
        return Result.OK(info);
    }

    @ApiOperation(value = "查询鱼缸温度限制列表", response = TemperatureLimitListInfo.class)
    @PostMapping("list")
    public Result<?> getTemperatureLimitList(@RequestBody TemperatureLimitListRequest request){
        List<TemperatureLimitListInfo> list = temperatureLimitService.getTemperatureLimitList(request);
        return Result.OK(list);
    }

    @ApiOperation("新增或修改鱼缸温度限制")
    @PostMapping("saveOrUpdate")
    public Result<?> saveOrUpdateTemperatureLimit(@RequestBody TemperatureLimitDetail dto){
        if (dto.getNodeId() == null) {
            return error(PARAM_MISSING);
        }
        Integer id = temperatureLimitService.saveOrUpdateTemperatureLimit(dto);
        return Result.OK(id);
    }

    @ApiOperation("删除鱼缸温度限制")
    @DeleteMapping("remove")
    public Result<?> removeTemperatureLimit(@RequestParam Integer id){
        boolean result = temperatureLimitService.removeTemperatureLimit(id);
        return result ? Result.OK() : error(ERROR);
    }
}
