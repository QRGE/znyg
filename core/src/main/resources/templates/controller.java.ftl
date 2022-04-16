package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ${package.Entity?replace(".po", ".bean")}.${dtoFullName};
import ${package.Entity?replace(".po", ".bean")}.${listInfo};
import ${package.Entity?replace(".po", ".bean")}.${listRequestName};
import ${package.Entity?replace(".po", ".bean")}.${pageRequestName};
import ${package.Service}.${table.serviceName};
import zhku.graduation.basic.vo.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import static zhku.graduation.basic.constant.SystemState.ResponseState.*;
import static zhku.graduation.basic.constant.HttpStatus.ERROR;

/**
 * @author ${author}
 * @since ${date}
 */
@Api(tags = "${table.comment!}")
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName}{
</#if>

    @Autowired
    private ${table.serviceName} ${entitySuffixNameSmallHump}Service;

    @ApiOperation(value = "查询${table.comment!}详情", response = ${dtoFullName}.class)
    @GetMapping("get")
    public Result<?> get${entitySuffixNameBigHump}(@RequestParam Integer id){
        ${dtoFullName} info = ${entitySuffixNameSmallHump}Service.get${entitySuffixNameBigHump}(id);
        return Result.OK(info);
    }

    @ApiOperation(value = "查询${table.comment!}列表", response = ${listInfo}.class)
    @PostMapping("list")
    public Result<?> get${entitySuffixNameBigHump}List(@RequestBody ${listRequestName} request){
        List<${listInfo}> list = ${entitySuffixNameSmallHump}Service.get${entitySuffixNameBigHump}List(request);
        return Result.OK(list);
    }

    @ApiOperation(value = "分页查询${table.comment!}列表", response = ${listInfo}.class)
    @PostMapping("page")
    public Result<?> get${entitySuffixNameBigHump}List(@RequestBody ${pageRequestName} request){
        handlePageRequest(request);
        IPage<${listInfo}> page = ${entitySuffixNameSmallHump}Service.page${entitySuffixNameBigHump}(request);
        return Result.OK(page);
    }

    @ApiOperation("新增或修改${table.comment!}")
    @PostMapping("edit")
    public Result<?> saveOrUpdate${entitySuffixNameBigHump}(@RequestBody ${dtoFullName} dto){
        boolean result = ${entitySuffixNameSmallHump}Service.saveOrUpdate${entitySuffixNameBigHump}(dto);
        return result ? Result.OK() : error(ERROR);
    }

    @ApiOperation("删除${table.comment!}")
    @DeleteMapping("remove")
    public Result<?> remove${entitySuffixNameBigHump}(@RequestParam Integer id){
        boolean result = ${entitySuffixNameSmallHump}Service.remove${entitySuffixNameBigHump}(id);
        return result ? Result.OK() : error(ERROR);
    }
}
</#if>
