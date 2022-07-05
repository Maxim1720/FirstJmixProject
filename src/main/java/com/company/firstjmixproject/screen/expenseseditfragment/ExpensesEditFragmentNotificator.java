package com.company.firstjmixproject.screen.expenseseditfragment;

import io.jmix.ui.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


@Component("expensesEditFragmentNotificator")
@Scope(value="vaadin-ui", proxyMode= ScopedProxyMode.INTERFACES)
public class ExpensesEditFragmentNotificator {

    @Autowired
    private Notifications notifications;

    public void sendNotification(String caption) {
        notifications
        .create()
                .withCaption(caption)
                .withType(Notifications.NotificationType.HUMANIZED)
                .show();
    }

}
