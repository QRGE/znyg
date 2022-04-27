package zhku.graduation.core.modules.node.controller;


import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhku.graduation.basic.controller.BaseController;
import zhku.graduation.basic.vo.Result;
import zhku.graduation.core.modules.node.entity.bean.NodeDetail;
import zhku.graduation.core.modules.node.entity.bean.NodeListInfo;
import zhku.graduation.core.modules.node.service.INodeService;

import java.util.List;

import static zhku.graduation.basic.constant.HttpStatus.ERROR;
import static zhku.graduation.basic.constant.HttpStatus.PARAM_MISSING;

/**
 * @author QR
 * @since 2022-01-29
 */
@Api(tags = "鱼缸节点信息表")
@RestController
@RequestMapping("/node/")
@Slf4j
public class NodeController extends BaseController {

    @Autowired
    private INodeService nodeService;

    @ApiOperation(value = "查询鱼缸节点信息表详情", response = NodeDetail.class)
    @GetMapping("get")
    public Result<?> getNode(@RequestParam Integer id){
        NodeDetail info = nodeService.getNode(id);
        return Result.OK(info);
    }

    @ApiOperation(value = "查询鱼缸节点信息表列表", response = NodeListInfo.class)
    @GetMapping("list")
    public Result<?> getNodeList(){
        List<NodeListInfo> list = nodeService.getNodeList();
        return Result.OK(list);
    }

    @ApiOperation("新增或修改鱼缸节点信息表")
    @PostMapping("edit")
    public Result<?> saveOrUpdateNode(@RequestBody NodeDetail dto){
        String name = dto.getName();
        if (StrUtil.isBlank(name)) {
            return error(PARAM_MISSING);
        }
        boolean result = nodeService.saveOrUpdateNode(dto);
        return result ? Result.OK() : error(ERROR);
    }

    @ApiOperation("删除鱼缸节点信息表")
    @DeleteMapping("{id}")
    public Result<?> removeNode(@PathVariable("id") Integer id){
        if (id == null) {
            return error(PARAM_MISSING);
        }
        boolean result = nodeService.removeNode(id);
        log.info("删除鱼缸节点id: {} 信息", id);
        return result ? Result.OK() : error(ERROR);
    }
}
