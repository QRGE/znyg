package zhku.graduation.core.modules.node.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import zhku.graduation.core.modules.node.entity.bean.NodeDetail;
import zhku.graduation.core.modules.node.entity.bean.NodeListInfo;
import zhku.graduation.core.modules.node.entity.bean.NodePageRequest;
import zhku.graduation.core.modules.node.entity.po.Node;
import zhku.graduation.core.modules.node.mapper.NodeMapper;
import zhku.graduation.core.modules.node.service.INodeService;

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

    private LambdaQueryWrapper<Node> baseQueryWrapper() {
        return Wrappers.lambdaQuery(Node.class)
                        .eq(Node::getIsDel, 0);
    }

    private LambdaUpdateWrapper<Node> baseUpdateWrapper() {
        return Wrappers.lambdaUpdate(Node.class)
                        .eq(Node::getIsDel, 0);
    }
}
