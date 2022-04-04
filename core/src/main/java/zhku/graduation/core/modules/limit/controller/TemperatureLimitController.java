package zhku.graduation.core.modules.limit.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhku.graduation.basic.controller.BaseController;
import zhku.graduation.basic.vo.Result;
import zhku.graduation.core.modules.limit.entity.bean.TemperatureLimitDetail;
import zhku.graduation.core.modules.limit.entity.bean.TemperatureLimitListInfo;
import zhku.graduation.core.modules.limit.entity.bean.TemperatureLimitListRequest;
import zhku.graduation.core.modules.limit.service.ITemperatureLimitService;

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
    @GetMapping("get")
    public Result<?> getTemperatureLimit(@RequestParam Integer id){
        TemperatureLimitDetail info = temperatureLimitService.getTemperatureLimit(id);
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
        boolean result = temperatureLimitService.saveOrUpdateTemperatureLimit(dto);
        return result ? Result.OK() : error(ERROR);
    }

    @ApiOperation("删除鱼缸温度限制")
    @DeleteMapping("remove")
    public Result<?> removeTemperatureLimit(@RequestParam Integer id){
        boolean result = temperatureLimitService.removeTemperatureLimit(id);
        return result ? Result.OK() : error(ERROR);
    }
}
