package com.company.firstjmixproject.screen.billcurrency;

import io.jmix.ui.screen.*;
import com.company.firstjmixproject.entity.BillCurrency;

@UiController("idid_BillCurrency.browse")
@UiDescriptor("bill-currency-browse.xml")
@LookupComponent("billCurrenciesTable")
public class BillCurrencyBrowse extends StandardLookup<BillCurrency> {
}