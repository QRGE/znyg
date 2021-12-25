package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
<#if dataSource??>
import com.baomidou.dynamic.datasource.annotation.DS;
</#if>
import ${package.Entity?replace(".po", ".bean")}.${dtoFullName};
import ${package.Entity?replace(".po", ".bean")}.${listInfo};
import ${package.Entity?replace(".po", ".bean")}.${listRequestName};
import ${package.Entity?replace(".po", ".bean")}.${pageRequestName};

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Override
    public List<${listInfo}> get${entitySuffixNameBigHump}List(${listRequestName} request) {
        LambdaQueryWrapper<${entity}> queryWrapper = baseQueryWrapper();
        List<${entity}> poList = list(queryWrapper);
        List<${listInfo}> dtoList = new ArrayList<>(poList.size());
        if (!poList.isEmpty()) {
            dtoList = poList.stream().map(${listInfo}::new).collect(Collectors.toList());
        }
        return dtoList;
    }

    @Override
    public IPage<${listInfo}> page${entitySuffixNameBigHump}(${pageRequestName} request) {
        LambdaQueryWrapper<${entity}> queryWrapper = baseQueryWrapper();
        IPage<${entity}> page = new Page<>(request.getPageNum(), request.getPageSize());
        page = this.page(page, queryWrapper);
        return page.convert(${listInfo}::new);
    }

    @Override
    public ${dtoFullName} get${entitySuffixNameBigHump}(Integer dataId) {
        ${entity} ${entity?uncap_first} = getById(dataId);
        return Optional.ofNullable(${entity?uncap_first})
                        .map(po -> new ${dtoFullName}().parseFromPo(po))
                        .orElse(null);
    }

    @Override
    public boolean saveOrUpdate${entitySuffixNameBigHump}(${dtoFullName} dto) {
        ${entity} ${entity?uncap_first} = new ${entity}();
        if(dto.getId() == null){
            ${entity?uncap_first} = ${entity?uncap_first}.init();
        }
        ${entity?uncap_first}.parseFromDto(dto);
        return saveOrUpdate(${entity?uncap_first});
    }

    @Override
    public boolean remove${entitySuffixNameBigHump}(Integer dataId) {
        return removeById(dataId);
    }

    private LambdaQueryWrapper<${entity}> baseQueryWrapper() {
        return Wrappers.lambdaQuery(${entity}.class)
                        .eq(${entity}::getIsDel, 0);
    }

    private LambdaUpdateWrapper<${entity}> baseUpdateWrapper() {
        return Wrappers.lambdaUpdate(${entity}.class)
                        .eq(${entity}::getIsDel, 0);
    }
}
</#if>
