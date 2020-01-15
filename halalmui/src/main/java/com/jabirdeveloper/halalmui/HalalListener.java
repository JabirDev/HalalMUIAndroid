package com.jabirdeveloper.halalmui;

import java.util.List;

public interface HalalListener {
    void ketikaSukses(List<HalalData> halalData);
    void ketikaTidakAdaData(String s);
    void ketikaGagal(Throwable t);
}
