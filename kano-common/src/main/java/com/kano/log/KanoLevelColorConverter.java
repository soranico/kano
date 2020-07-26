package com.kano.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

/**
 * <pre>
 * @title com.kano.log.KanoLogColorConverter
 * @description
 *        <pre>
 *          日志不同级别颜色
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/7/26
 *
 * </pre>
 */
public class KanoLevelColorConverter extends ForegroundCompositeConverterBase<ILoggingEvent> {


    @Override
    protected String getForegroundColorCode(ILoggingEvent event) {
        switch (event.getLevel().toInt()) {
            case Level.DEBUG_INT:
                return ANSIConstants.MAGENTA_FG;
            case Level.INFO_INT:
                return ANSIConstants.GREEN_FG;
            case Level.WARN_INT:
                return ANSIConstants.YELLOW_FG;
            case Level.ERROR_INT:
                return ANSIConstants.RED_FG;
            default:
                return ANSIConstants.DEFAULT_FG;
        }
    }

}
