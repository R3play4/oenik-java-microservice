package hu.yokudlela.foodAndDrinks.utils.logging;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import hu.yokudlela.foodAndDrinks.spring.ApplicationContextProvider;
import hu.yokudlela.foodAndDrinks.utils.request.RequestBean;
import net.logstash.logback.marker.ObjectAppendingMarker;
import static hu.yokudlela.foodAndDrinks.utils.logging.CustomRequestLoggingFilter.USER_ID;
import static hu.yokudlela.foodAndDrinks.utils.logging.CustomRequestLoggingFilter.NOT_DEF;

public class UserIdMessageConverter extends ClassicConverter {

    ApplicationContextProvider appContext = new ApplicationContextProvider();


    @Override
    @AspectLogger
    public String convert(ILoggingEvent event) {

        try {
            RequestBean request = appContext.getApplicationContext().getBean("requestScopedBean", RequestBean.class);
            if (request != null) {
                return ("" + request.getUser());
            }
        } catch (Exception e) {
            if (event != null && event.getArgumentArray() != null) {
                ObjectAppendingMarker tmp;
                for (Object bean : event.getArgumentArray()) {
                    if (bean instanceof ObjectAppendingMarker) {
                        tmp = (ObjectAppendingMarker) bean;
                        if (USER_ID.equals(tmp.getFieldName())) {
                            return "" + tmp.getFieldValue();
                        }
                    }
                }
            }
        }
        return NOT_DEF;
    }

}
