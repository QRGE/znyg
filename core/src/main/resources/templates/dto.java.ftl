package ${package.Entity?replace(".po", ".bean")};

<#if swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Getter;
import lombok.Setter;
</#if>
import ${package.Entity}.${entity};
import java.util.Date;

/**
 * <p>
 * ${table.comment!}
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if entityLombokModel>
@Getter
@Setter
</#if>
<#if swagger>
@ApiModel(value = "${table.comment!}详情")
</#if>
public class ${dtoFullName} {

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#--暂时只能在这里加不需要在DTO出现的字段-->
    <#if field.propertyName != "isDel" && field.propertyName != "createTime" && field.propertyName != "updateTime">
    <#if field.comment!?length gt 0>
    @ApiModelProperty("${field.comment}")
    </#if>
    private ${field.propertyType} ${field.propertyName};
    </#if>

</#list>
<#------------  END 字段循环遍历  ---------->
    public ${dtoFullName} parseFromPo(${entity} po) {
<#list table.fields as field>
    <#if field.propertyName != "isDel"
        && field.propertyName != "createTime" && field.propertyName != "createBy"
        && field.propertyName != "updateTime" && field.propertyName != "updateBy"
        && field.propertyName != "deleteTime" && field.propertyName != "deleteBy">
        ${field.propertyName} = po.get${field.propertyName?cap_first}();
    </#if>
</#list>
        return this;
    }
}
