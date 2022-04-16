package zhku.graduation.core.modules.record.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhku.graduation.basic.controller.BaseController;
import zhku.graduation.basic.vo.Page;
import zhku.graduation.basic.vo.Result;
import zhku.graduation.core.modules.node.service.INodeService;
import zhku.graduation.core.modules.record.entity.bean.LatestRecord;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordDetail;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordListInfo;
import zhku.graduation.core.modules.record.entity.bean.RealTimeRecord;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;
import zhku.graduation.core.modules.record.entity.request.MonitorRecordPageRequest;
import zhku.graduation.core.modules.record.service.IMonitorRecordService;

import java.util.Date;

import static zhku.graduation.basic.constant.HttpStatus.ERROR;
import static zhku.graduation.basic.constant.HttpStatus.PARAM_MISSING;

/**
 * @author QR
 * @since 2022-02-14
 */
@Api(tags = "监测记录表")
@RestController
@RequestMapping("/record/")
@Slf4j
public class MonitorRecordController extends BaseController {

    @Autowired
    private IMonitorRecordService monitorRecordService;
    @Autowired
    private INodeService nodeService;

    @ApiOperation(value = "查询监测记录表的最新数据", response = MonitorRecordDetail.class)
    @GetMapping("latest")
    public Result<?> getMonitorRecord(@RequestParam Integer nodeId){
        if (nodeId == null) {
            return error(PARAM_MISSING);
        }
        LambdaQueryWrapper<MonitorRecord> wrapper = Wrappers.lambdaQuery(MonitorRecord.class)
                .eq(MonitorRecord::getNodeId, nodeId)
                .orderByDesc(MonitorRecord::getRecordTime)
                .last("limit 1");
        MonitorRecord record = monitorRecordService.getOne(wrapper);
        LatestRecord latestRecord = new LatestRecord();
        if (record != null) {
            latestRecord = latestRecord.parseFromPO(record);
        }
        return Result.OK(latestRecord);
    }

    @ApiOperation("获取某个鱼缸节点的最新的5条数据")
    @GetMapping("node")
    public Result<?> getNodeLatestRecord(@RequestParam Integer nodeId){
        RealTimeRecord record = nodeService.getNodeLatestRecord(nodeId);
        return Result.OK(record);
    }

    @ApiOperation(value = "分页查询监测记录表列表", response = MonitorRecordListInfo.class)
    @PostMapping("page")
    public Result<?> getMonitorRecordList(@RequestBody MonitorRecordPageRequest request){
        handlePageRequest(request);
        // 默认是正序, 除非传入倒序传入其他乱七八糟的都是正序
        request.handleOrderType();
        Date startTime = request.getStartTime();
        Date endTime = request.getEndTime();
        Integer nodeId = request.getNodeId();
        Integer orderType = request.getOrderType();
        Integer pageStart = request.getPageStart();
        Integer pageSize = request.getPageSize();
        Page<MonitorRecordListInfo> result = monitorRecordService.pageMonitorRecords(nodeId, startTime, endTime, orderType, pageStart, pageSize);
        return Result.OK(result);
    }

    @ApiOperation("新增或修改监测记录表")
    @PostMapping("edit")
    public Result<?> saveOrUpdateMonitorRecord(@RequestBody MonitorRecordDetail dto){
        boolean result = monitorRecordService.saveOrUpdateMonitorRecord(dto);
        return result ? Result.OK() : error(ERROR);
    }

    @ApiOperation("删除监测记录表")
    @DeleteMapping("remove/{recordId}")
    public Result<?> removeMonitorRecord(@PathVariable Integer recordId){
        boolean result = monitorRecordService.removeMonitorRecord(recordId);
        return result ? Result.OK() : error(ERROR);
    }
}
