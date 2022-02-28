package zhku.graduation.core.modules.record.controller;


import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhku.graduation.basic.controller.BaseController;
import zhku.graduation.basic.vo.Page;
import zhku.graduation.basic.vo.Result;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordDetail;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordListInfo;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;
import zhku.graduation.core.modules.record.entity.request.MonitorRecordPageRequest;
import zhku.graduation.core.modules.record.service.IMonitorRecordService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @ApiOperation("获取某个鱼缸节点的最新的5条数据")
    @GetMapping("node/{nodeId}")
    public Result<?> get(@PathVariable Integer nodeId){
        LambdaQueryWrapper<MonitorRecord> wrapper = Wrappers.lambdaQuery(MonitorRecord.class)
                .eq(MonitorRecord::getNodeId, nodeId)
                .orderByDesc(MonitorRecord::getRecordTime)
                .last("limit 5");
        List<MonitorRecord> recordList = monitorRecordService.list(wrapper);
        JSONObject result = new JSONObject();
        result.set("temperatures", recordList.stream()
                .map(MonitorRecord::getTemperature)
                .collect(Collectors.toList()));
        result.set("dates", recordList.stream()
                .map(po -> {
                    Date recordTime = po.getRecordTime();
                    return DateUtil.format(recordTime, "HH:mm:ss");
                })
                .collect(Collectors.toList()));
        int first = 0;
        result.set("heater", recordList.get(first).getHeaterStatus());
        result.set("light", recordList.get(first).getLightStatus());
        result.set("degerming", recordList.get(first).getDegermingStatus());
        return Result.OK(result);
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
        Page<MonitorRecordListInfo> result = monitorRecordService.pageMonitorRecords(nodeId, startTime, endTime,
                orderType, pageStart, pageSize);
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
