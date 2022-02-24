package zhku.graduation.core.modules.quartz.job;

import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import zhku.graduation.core.modules.node.service.INodeService;
import zhku.graduation.core.modules.record.entity.bean.MonitorRecordListInfo;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;
import zhku.graduation.core.modules.record.service.IMonitorRecordService;
import zhku.graduation.core.modules.socket.WebSocket;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qr
 * @date 2022/2/14 14:45
 */
@Component
@Slf4j
public class SendRecordJob extends QuartzJobBean {

    @Autowired
    private IMonitorRecordService monitorRecordService;
    @Autowired
    private INodeService nodeService;
    @Autowired
    private WebSocket webSocket;

    /**
     * 推送最新的监控历史记录
     * 或许这种方法不是很方便, 也不是很有效, 还得用前端定时器轮训吧。。。
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        // 发送消息对象
        JSONArray msg = new JSONArray();
        // 查询没个鱼缸节点的最新的十条数据
        Integer size = nodeService.getNodeSize();
        for (int nodeId = 1; nodeId <= size; nodeId++) {
            LambdaQueryWrapper<MonitorRecord> wrapper = Wrappers.lambdaQuery(MonitorRecord.class)
                    .eq(MonitorRecord::getNodeId, nodeId)
                    .orderByDesc(MonitorRecord::getRecordTime)
                    .last("limit 10");
            List<MonitorRecord> records = monitorRecordService.list(wrapper);
            List<MonitorRecordListInfo> infos = records.stream().map(MonitorRecordListInfo::new)
                    .collect(Collectors.toList());
            msg.add(infos);
        }
        webSocket.sendAllMessage(msg.toString());
    }
}
