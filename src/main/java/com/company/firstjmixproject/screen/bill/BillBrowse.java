package com.company.firstjmixproject.screen.bill;

import io.jmix.ui.screen.*;
import com.company.firstjmixproject.entity.Bill;

@UiController("idid_Bill.browse")
@UiDescriptor("bill-browse.xml")
@LookupComponent("billsTable")
public class BillBrowse extends StandardLookup<Bill> {
}