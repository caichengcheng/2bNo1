package ratelimit;

/**
 * @author caichengcheng
 * date:2019-12-09
 */
public interface IMyRateLimit {
    boolean isOverLimit();

    boolean visit();

    int currentQps();
}
