package zhku.graduation.core.modules.limit.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhku.graduation.basic.controller.BaseController;
import zhku.graduation.basic.vo.Page;
import zhku.graduation.basic.vo.Result;
import zhku.graduation.core.modules.limit.entity.bean.CommandRecordHostDetail;
import zhku.graduation.core.modules.limit.entity.bean.CommandRecordHostListInfo;
import zhku.graduation.core.modules.limit.entity.bean.CommandRecordHostPageRequest;
import zhku.graduation.core.modules.limit.service.ICommandRecordHostService;

import static zhku.graduation.basic.constant.HttpStatus.ERROR;

/**
 * @author QR
 * @since 2022-04-16
 */
@Api(tags = "上位机 端控制命令记录")
@RestController
@RequestMapping("/command/host")
public class CommandRecordHostController extends BaseController {

    @Autowired
    private ICommandRecordHostService commandRecordHostService;

    @ApiOperation(value = "分页查询上位机 端控制命令记录列表", response = CommandRecordHostListInfo.class)
    @PostMapping("page")
    public Result<?> getCommandRecordHostList(@RequestBody CommandRecordHostPageRequest request){
        handlePageRequest(request);
        Page<CommandRecordHostDetail> page = commandRecordHostService.pageCommandRecordHost(request);
        return Result.OK(page);
    }

    @ApiOperation("删除上位机 端控制命令记录")
    @DeleteMapping("remove/{id}")
    public Result<?> removeCommandRecordHost(@PathVariable Integer id){
        boolean result = commandRecordHostService.removeCommandRecordHost(id);
        return result ? Result.OK() : error(ERROR);
    }
}
