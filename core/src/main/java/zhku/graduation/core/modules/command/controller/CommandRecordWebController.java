package zhku.graduation.core.modules.command.controller;


import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhku.graduation.basic.constant.HttpStatus;
import zhku.graduation.basic.controller.BaseController;
import zhku.graduation.basic.vo.Page;
import zhku.graduation.basic.vo.Result;
import zhku.graduation.core.modules.command.entity.bean.CommandRecordWebDetail;
import zhku.graduation.core.modules.command.entity.po.CommandRecordWeb;
import zhku.graduation.core.modules.command.entity.request.CommandBody;
import zhku.graduation.core.modules.command.entity.request.CommandRecordWebPageRequest;
import zhku.graduation.core.modules.command.service.ICommandRecordWebService;

/**
 * @author QR
 * @since 2022-03-01
 */
@Api(tags = "Web 端控制命令记录")
@RestController
@RequestMapping("/command/")
public class CommandRecordWebController extends BaseController {

    @Autowired
    private ICommandRecordWebService commandRecordWebService;

    @ApiOperation(value = "查询Web 端控制命令记录详情", response = CommandRecordWebDetail.class)
    @GetMapping("get")
    public Result<?> getLatestCommandRecordWeb(){
        CommandRecordWebDetail info = commandRecordWebService.getCommandRecordWeb();
        return Result.OK(info);
    }

    @ApiOperation(value = "分页查询控制目标")
    @PostMapping("page")
    public Result<?> get(@RequestBody CommandRecordWebPageRequest request){
        handlePageRequest(request);
        request.handleOrderType();
        Page<CommandRecordWebDetail> page = commandRecordWebService.pageCommands(request);
        return Result.OK(page);
    }

    @ApiOperation(value = "查询Web 端控制命令记录详情", response = CommandRecordWebDetail.class)
    @GetMapping("get/{id}")
    public Result<?> getCommandRecordWeb(@PathVariable Integer id){
        CommandRecordWeb record = commandRecordWebService.getById(id);
        return record == null ? error(HttpStatus.ERROR) : Result.OK(record.getCommandStatus());
    }

    @ApiOperation("新增或修改Web 端控制命令记录")
    @PostMapping("save")
    public Result<?> saveOrUpdateCommandRecordWeb(@RequestBody CommandBody body){
        if (StrUtil.isBlank(body.getCommand())) {
            return error(HttpStatus.PARAM_MISSING);
        }
        return Result.OK();
    }

    @ApiOperation("删除Web 端控制命令记录")
    @DeleteMapping("remove/{id}")
    public Result<?> removeCommandRecordWeb(@PathVariable Integer id){
        boolean result = commandRecordWebService.removeCommandRecordWeb(id);
        return result ? Result.OK() : error(HttpStatus.ERROR);
    }
}
