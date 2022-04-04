package zhku.graduation.core.modules.quartz.job;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import zhku.graduation.core.modules.node.service.INodeService;
import zhku.graduation.core.modules.record.service.IMonitorRecordService;
import zhku.graduation.core.modules.socket.WebSocket;

import java.util.List;

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
        List<Integer> nodeIds = nodeService.getNodeIds();
        for (Integer nodeId : nodeIds) {
            webSocket.sendOneMessage(nodeId+"", JSONUtil.toJsonStr(nodeService.getNodeLatestRecord(nodeId)));
        }
        webSocket.sendAllMessage(msg.toString());
    }
}
