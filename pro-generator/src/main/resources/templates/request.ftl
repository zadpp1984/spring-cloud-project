package ${genpkg};
import lombok.Data;
import java.util.List;
import java.lang.Integer;
import com.wzy.common.page.RequestPage;
<#list importPkg as pkg>
import ${pkg};
</#list>

/**
 * <p>
    * ${tableComment}
    * </p>
 *
 * @author 王振宇
 * @since ${generatorDate}
 */
@Data
public class ${className}Request extends RequestPage {

<#list fields as field>
   /**
    * ${field.comment}
    */
    private <#if field.type == 'Date'>String<#else>${field.type}</#if> ${field.fieldName};

</#list>
   /**
    * in 查询操作
    */
    private List<Integer> ids;
}
