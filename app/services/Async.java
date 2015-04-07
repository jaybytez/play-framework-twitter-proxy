package services;

import java.util.function.Supplier;

import play.core.j.HttpExecutionContext;
import play.libs.Akka;
import play.libs.F.Promise;
import scala.concurrent.ExecutionContext;

public interface Async {

    public static long GET_WAIT_DEFAULT_TIME = 10000;
    public static long GET_WAIT_LONG_TIME = 60000;
    public static final int EVENT_CACHE_TIMEOUT = 60 * 5;

    default <A> Promise<A> async(Supplier<A> f) {
        return Promise.promise(f::get);
    }

    default <A> Promise<A> async(Supplier<A> f, ExecutionContext ec) {
        return Promise.promise(f::get, HttpExecutionContext.fromThread(ec));
    }

}
