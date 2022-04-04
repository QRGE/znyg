package zhku.graduation.core.modules.node.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import zhku.graduation.core.modules.node.entity.bean.NodeDetail;
import zhku.graduation.core.modules.node.entity.bean.NodeListInfo;
import zhku.graduation.core.modules.node.entity.bean.NodePageRequest;
import zhku.graduation.core.modules.node.entity.po.Node;
import zhku.graduation.core.modules.record.entity.bean.RealTimeRecord;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 鱼缸节点信息表服务类
 * </p>
 *
 * @author QR
 * @since 2022-01-29
 */
public interface INodeService extends IService<Node> {

    Integer getNodeSize();

    List<Integer> getNodeIds();

    Map<Integer, String> getIdToName();

    List<NodeListInfo> getNodeList();

    IPage<NodeListInfo> pageNode(NodePageRequest request);

    NodeDetail getNode(Integer dataId);

    boolean saveOrUpdateNode(NodeDetail dto);

    boolean removeNode(Integer dataId);

    RealTimeRecord getNodeLatestRecord(Integer nodeId);
}
