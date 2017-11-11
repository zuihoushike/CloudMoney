package name.hd.cloudmoney.test;

import android.test.AndroidTestCase;

import name.hd.cloudmoney.db.StateDB;
import name.hd.cloudmoney.db.UserDB;

/**
 * Created by AkiNobunaga on 2017/10/28.
 */

public class Test extends AndroidTestCase {
    StateDB sdb = new StateDB();
    UserDB udb=new UserDB();

    public void testAdd() {
        udb.add(getContext(),"22");
//        sdb.add(getContext(), "选择");
    }
}
