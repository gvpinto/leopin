package com.leopin.parkfifty.client.presenters;

import com.google.gwt.place.shared.Place;
import com.leopin.parkfifty.shared.messages.AppMessages;

public interface Presenter {

        void goTo(Place place);
        void bind();
        AppMessages messages();

}
