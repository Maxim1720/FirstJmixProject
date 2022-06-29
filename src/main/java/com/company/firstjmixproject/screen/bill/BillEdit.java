package com.company.firstjmixproject.screen.bill;

import io.jmix.ui.screen.*;
import com.company.firstjmixproject.entity.Bill;

@UiController("idid_Bill.edit")
@UiDescriptor("bill-edit.xml")
@EditedEntityContainer("billDc")
public class BillEdit extends StandardEditor<Bill> {
}