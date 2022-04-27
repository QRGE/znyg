package ${package.Entity?replace(".po", ".bean")};

<#if swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
</#if>
import zhku.graduation.basic.request.BasePageRequest;

/**
 * @author ${author}
 * @since ${date}
 */
<#if entityLombokModel>
@Getter
@Setter
@ToString
</#if>
<#if swagger>
@ApiModel(value = "${table.comment!}分页请求")
</#if>
public class ${pageRequestName} extends BasePageRequest{


}
