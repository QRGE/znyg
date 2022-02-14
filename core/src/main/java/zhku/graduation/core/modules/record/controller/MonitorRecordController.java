package zhku.graduation.core.modules.record.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhku.graduation.basic.controller.BaseController;
import zhku.graduation.basic.vo.Result;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordDetail;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordListInfo;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordPageRequest;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;
import zhku.graduation.core.modules.record.service.IMonitorRecordService;

import static zhku.graduation.basic.constant.HttpStatus.ERROR;

/**
 * @author QR
 * @since 2022-02-14
 */
@Api(tags = "监测记录表")
@RestController
@RequestMapping("/record/")
public class MonitorRecordController extends BaseController {

    @Autowired
    private IMonitorRecordService monitorRecordService;

    @ApiOperation(value = "查询监测记录表详情", response = MonitorRecordDetail.class)
    @GetMapping("get")
    public Result<?> getMonitorRecord(@RequestParam Integer id){
//        MonitorRecordDetail info = monitorRecordService.getMonitorRecord(id);
        MonitorRecord record = monitorRecordService.getById(id);
        return Result.OK(record);
    }

    @ApiOperation(value = "分页查询监测记录表列表", response = MonitorRecordListInfo.class)
    @PostMapping("page")
    public Result<?> getMonitorRecordList(@RequestBody MonitorRecordPageRequest request){
        handlePageRequest(request);
        IPage<MonitorRecordListInfo> page = monitorRecordService.pageMonitorRecord(request);
        return Result.OK(page);
    }

    @ApiOperation("新增或修改监测记录表")
    @PostMapping("edit")
    public Result<?> saveOrUpdateMonitorRecord(@RequestBody MonitorRecordDetail dto){
        boolean result = monitorRecordService.saveOrUpdateMonitorRecord(dto);
        return result ? Result.OK() : error(ERROR);
    }

    @ApiOperation("删除监测记录表")
    @DeleteMapping("remove")
    public Result<?> removeMonitorRecord(@RequestParam Integer id){
        boolean result = monitorRecordService.removeMonitorRecord(id);
        return result ? Result.OK() : error(ERROR);
    }
}
