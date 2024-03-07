import com.xiangzhi.xxsdk.APIException;
import com.xiangzhi.xxsdk.AppAuth;
import com.xiangzhi.xxsdk.api.ProductAPI;
import com.xiangzhi.xxsdk.bean.BindIpList;
import com.xiangzhi.xxsdk.bean.Proxy;
import com.xiangzhi.xxsdk.request.GetProxyRequest;

import java.util.Arrays;
import java.util.List;

/**
 * 短效代理使用示例
 */
public class UseProductAPI {
    public static void main(String[] args) throws APIException {
        ProductAPI api = new ProductAPI(new AppAuth("key", "secret"));

        //绑定白名单（将当前用户端ip地址绑定至白名单列表）
        api.bindIp(null);

        //获取白名单列表
        BindIpList list = api.getBindIpList(null);
        System.out.println(Arrays.toString(list.getBindIpsList()));

        GetProxyRequest req = new GetProxyRequest();
        //设置提取数量
        req.setCnt(1);
        List<Proxy> proxies = api.getProxies(req);
        proxies.forEach(p -> System.out.println(p.getIp() + ":" + p.getPort()));

        //获取包量套餐的剩余数量
        System.out.println(api.getUsage().getRemaining());
    }
}
