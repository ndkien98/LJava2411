//package vn.com.t3h.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.jcache.JCacheCacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.cache.Caching;
//import javax.cache.spi.CachingProvider;
//import java.net.URISyntaxException;
//
//
//@Configuration
//@EnableCaching
//@Slf4j
//public class CacheConfig {
//
//    @Bean
//    public JCacheCacheManager cacheManager() {
//        CachingProvider cachingProvider = Caching.getCachingProvider();
//        javax.cache.CacheManager cacheManager = null;
//        try {
//            cacheManager = cachingProvider.getCacheManager(
//                    getClass().getResource("/ehcache.xml").toURI(),
//                    getClass().getClassLoader()
//            );
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//            log.error("create cache manager error");
//        }
//        return new JCacheCacheManager(cacheManager);
//    }
//}
