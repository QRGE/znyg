package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

import java.util.List;
import ${package.Entity?replace(".po", ".bean")}.${dtoFullName};
import ${package.Entity?replace(".po", ".bean")}.${listInfo};
import ${package.Entity?replace(".po", ".bean")}.${listRequestName};
import ${package.Entity?replace(".po", ".bean")}.${pageRequestName};

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 * ${table.comment!}服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    List<${listInfo}> get${entitySuffixNameBigHump}List(${listRequestName} request);

    IPage<${listInfo}> page${entitySuffixNameBigHump}(${pageRequestName} request);

    ${dtoFullName} get${entitySuffixNameBigHump}(Integer dataId);

    boolean saveOrUpdate${entitySuffixNameBigHump}(${dtoFullName} dto);

    boolean remove${entitySuffixNameBigHump}(Integer dataId);
}
</#if>
