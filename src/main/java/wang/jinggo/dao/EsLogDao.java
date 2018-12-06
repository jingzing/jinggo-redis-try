package wang.jinggo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import wang.jinggo.domain.es.EsLog;

/**
 * @author wangyj
 * @description
 * @create 2018-10-04 18:21
 **/
public interface EsLogDao extends ElasticsearchRepository<EsLog, String> {

    /**
     * 通过类型获取
     * @param type
     * @return
     */
    Page<EsLog> findByLogType(Integer type, Pageable pageable);
}
