package zhku.graduation.core.modules.node.service;

import zhku.graduation.core.modules.node.entity.po.Node;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import zhku.graduation.core.modules.node.entity.bean.NodeDetail;
import zhku.graduation.core.modules.node.entity.bean.NodeListInfo;
import zhku.graduation.core.modules.node.entity.bean.NodePageRequest;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 * 鱼缸节点信息表服务类
 * </p>
 *
 * @author QR
 * @since 2022-01-29
 */
public interface INodeService extends IService<Node> {

    List<NodeListInfo> getNodeList();

    IPage<NodeListInfo> pageNode(NodePageRequest request);

    NodeDetail getNode(Integer dataId);

    boolean saveOrUpdateNode(NodeDetail dto);

    boolean removeNode(Integer dataId);
}
