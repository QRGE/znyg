package zhku.graduation.core.modules.node.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhku.graduation.core.modules.node.entity.bean.NodeDetail;
import zhku.graduation.core.modules.node.entity.bean.NodeListInfo;
import zhku.graduation.core.modules.node.entity.bean.NodePageRequest;
import zhku.graduation.core.modules.node.entity.po.Node;
import zhku.graduation.core.modules.node.mapper.NodeMapper;
import zhku.graduation.core.modules.node.service.INodeService;
import zhku.graduation.core.modules.record.entity.bean.RealTimeRecord;
import zhku.graduation.core.modules.record.entity.po.MonitorRecord;
import zhku.graduation.core.modules.record.service.IMonitorRecordService;

import java.util.*;
import java.util.stream.Collectors;


/**
 * <p>
 * 鱼缸节点信息表 服务实现类
 * </p>
 *
 * @author QR
 * @since 2022-01-29
 */
@Service
public class NodeServiceImpl extends ServiceImpl<NodeMapper, Node> implements INodeService {

    @Autowired
    private IMonitorRecordService monitorRecordService;

    @Override
    public Integer getNodeSize() {
        return baseMapper.selectCount(Wrappers.lambdaQuery(Node.class)).intValue();
    }

    @Override
    public List<Integer> getNodeIds() {
        List<Node> nodes = list();
        List<Integer> ids = null;
        if (CollectionUtil.isNotEmpty(nodes)) {
            ids = nodes.stream().map(Node::getId).collect(Collectors.toList());
        }
        return ids;
    }

    @Override
    public Map<Integer, String> getIdToName() {
        List<Node> nodes = list();
        HashMap<Integer, String> idToName = new HashMap<>();
        nodes.forEach(node -> {
            idToName.put(node.getId(), node.getName());
        });
        return idToName;
    }

    @Override
    public List<NodeListInfo> getNodeList() {
        LambdaQueryWrapper<Node> queryWrapper = baseQueryWrapper();
        List<Node> poList = list(queryWrapper);
        List<NodeListInfo> dtoList = new ArrayList<>(poList.size());
        if (!poList.isEmpty()) {
            dtoList = poList.stream().map(NodeListInfo::new).collect(Collectors.toList());
        }
        return dtoList;
    }

    @Override
    public IPage<NodeListInfo> pageNode(NodePageRequest request) {
        LambdaQueryWrapper<Node> queryWrapper = baseQueryWrapper();
        IPage<Node> page = new Page<>(request.getPage(), request.getPageSize());
        page = this.page(page, queryWrapper);
        return page.convert(NodeListInfo::new);
    }

    @Override
    public NodeDetail getNode(Integer dataId) {
        Node node = getById(dataId);
        return Optional.ofNullable(node)
                        .map(po -> new NodeDetail().parseFromPo(po))
                        .orElse(null);
    }

    @Override
    public boolean saveOrUpdateNode(NodeDetail dto) {
        Node node = new Node();
        if(dto.getId() == null){
            node = node.init();
        }
        node.parseFromDto(dto);
        return saveOrUpdate(node);
    }

    @Override
    public boolean removeNode(Integer dataId) {
        return removeById(dataId);
    }

    @Override
    public RealTimeRecord getNodeLatestRecord(Integer nodeId) {
        LambdaQueryWrapper<MonitorRecord> wrapper = Wrappers.lambdaQuery(MonitorRecord.class)
                .eq(MonitorRecord::getNodeId, nodeId)
                .orderByDesc(MonitorRecord::getRecordTime)
                .last("limit 5");
        List<MonitorRecord> recordList = monitorRecordService.list(wrapper);
        // 按时间正序排序
        recordList.sort(Comparator.comparing(MonitorRecord::getRecordTime));
        RealTimeRecord record = new RealTimeRecord();
        if (CollectionUtil.isNotEmpty(recordList)) {
            int size = recordList.size();
            List<Double> temperatures = recordList.stream()
                    .map(MonitorRecord::getTemperature)
                    .collect(Collectors.toList());
            record.setTemperatures(temperatures);
            List<String> dates = recordList.stream()
                    .map(po -> {
                        Date recordTime = po.getRecordTime();
                        return DateUtil.format(recordTime, "HH:mm:ss");
                    }).collect(Collectors.toList());
            record.setDates(dates);
            // 设置最新的一条数据
            record = record.parseFromPO(recordList.get(size-1));
        }
        return record;
    }

    private LambdaQueryWrapper<Node> baseQueryWrapper() {
        return Wrappers.lambdaQuery(Node.class)
                        .eq(Node::getIsDel, 0);
    }

    private LambdaUpdateWrapper<Node> baseUpdateWrapper() {
        return Wrappers.lambdaUpdate(Node.class)
                        .eq(Node::getIsDel, 0);
    }
}
