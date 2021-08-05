/*
 * Copyright (C) 2021 iofairy, <https://github.com/io-fairy/functional>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iofairy.tcf;

import com.iofairy.lambda.*;
import com.iofairy.top.G;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Support some simple try-catch block<br>
 * 仅支持一些简单的try-catch块<br><br>
 * <b>Examples:</b><br>
 * non-use of <b>{@code Try}</b>: <br>
 * <pre>
 *      try {
 *          Thread.sleep(500);
 *      } catch (InterruptedException e) {
 *          e.printStackTrace();
 *      }
 * </pre>
 * use <b>{@code Try}</b>: <br>
 * <pre>
 *      Try.tcf(() -&gt; Thread.sleep(500));
 * </pre>
 * @since 0.0.4
 */
public class Try {

    private final static Logger log = Logger.getLogger(Try.class.getName());

    private Try() {
    }

    public static void tcf(VT0<Throwable> tryAction) {
        tcf(tryAction, true);
    }

    public static void tcf(VT0<Throwable> tryAction, boolean isPrintTrace) {
        tcf(tryAction, isPrintTrace, 0);
    }

    public static void tcf(VT0<Throwable> tryAction, boolean isPrintTrace, long delaySeconds) {
        tcf(tryAction, isPrintTrace, TimeUnit.SECONDS, delaySeconds);
    }

    public static void tcf(VT0<Throwable> tryAction, boolean isPrintTrace, TimeUnit timeUnit, long delay) {
        try {
            timeUnit.sleep(delay);
            tryAction.$();
        } catch (Throwable e) {
            if (isPrintTrace) {
                log.severe("Exception in `tcf()` method:\n" + G.stackTrace(e));
            }
        }
    }

    public static <R> R tcf(RT0<R, Throwable> tryAction) {
        return tcf(tryAction, true);
    }

    public static <R> R tcf(RT0<R, Throwable> tryAction, boolean isPrintTrace) {
        return tcf(tryAction, null, isPrintTrace);
    }

    public static <R> R tcf(RT0<R, Throwable> tryAction, R defaultReturn, boolean isPrintTrace) {
        return tcf(tryAction, defaultReturn, isPrintTrace, 0);
    }

    public static <R> R tcf(RT0<R, Throwable> tryAction, R defaultReturn, boolean isPrintTrace, long delaySeconds) {
        return tcf(tryAction, defaultReturn, isPrintTrace, TimeUnit.SECONDS, delaySeconds);
    }

    public static <R> R tcf(RT0<R, Throwable> tryAction, R defaultReturn, boolean isPrintTrace, TimeUnit timeUnit, long delay) {
        try {
            timeUnit.sleep(delay);
            return tryAction.$();
        } catch (Throwable e) {
            if (isPrintTrace) {
                log.severe("Exception in `tcf()` method:\n" + G.stackTrace(e));
            }
        }
        return defaultReturn;
    }

    public static void tcf(VT0<Throwable> tryAction, V1<Throwable> catchAction) {
        tcf(tryAction, catchAction, 0);
    }

    public static void tcf(VT0<Throwable> tryAction, V1<Throwable> catchAction, long delaySeconds) {
        tcf(tryAction, catchAction, TimeUnit.SECONDS, delaySeconds);
    }

    public static void tcf(VT0<Throwable> tryAction, V1<Throwable> catchAction, TimeUnit timeUnit, long delay) {
        try {
            timeUnit.sleep(delay);
            tryAction.$();
        } catch (Throwable e) {
            if (catchAction != null) catchAction.$(e);
        }
    }

    public static <R> R tcf(RT0<R, Throwable> tryAction, V1<Throwable> catchAction) {
        return tcf(tryAction, null, catchAction);
    }

    public static <R> R tcf(RT0<R, Throwable> tryAction, R defaultReturn, V1<Throwable> catchAction) {
        return tcf(tryAction, defaultReturn, catchAction, 0);
    }

    public static <R> R tcf(RT0<R, Throwable> tryAction, R defaultReturn, V1<Throwable> catchAction, long delaySeconds) {
        return tcf(tryAction, defaultReturn, catchAction, TimeUnit.SECONDS, delaySeconds);
    }

    public static <R> R tcf(RT0<R, Throwable> tryAction, R defaultReturn, V1<Throwable> catchAction, TimeUnit timeUnit, long delay) {
        try {
            timeUnit.sleep(delay);
            return tryAction.$();
        } catch (Throwable e) {
            if (catchAction != null) catchAction.$(e);
        }
        return defaultReturn;
    }

}
