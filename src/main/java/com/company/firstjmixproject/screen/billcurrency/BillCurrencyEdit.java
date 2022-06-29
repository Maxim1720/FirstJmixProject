package com.company.firstjmixproject.screen.billcurrency;

import io.jmix.ui.screen.*;
import com.company.firstjmixproject.entity.BillCurrency;

@UiController("idid_BillCurrency.edit")
@UiDescriptor("bill-currency-edit.xml")
@EditedEntityContainer("billCurrencyDc")
public class BillCurrencyEdit extends StandardEditor<BillCurrency> {
}