package wang.jinggo.service.mybatis;

import com.baomidou.mybatisplus.service.IService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import wang.jinggo.domain.Permission;

import java.util.List;

/**
 * Created by gz12 on 2018-09-28.
 */
@CacheConfig(cacheNames = "userPermission")
public interface IPermissionService extends IService<Permission> {

    /**
     * 通过用户id获取
     * @param userId
     * @return
     */
    @Cacheable(key = "#userId")
    List<Permission> findByUserId(String userId);
}