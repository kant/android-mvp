/*
 * Copyright (c) 2017 moovel Group GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.moovel.mvp;

import android.content.Context;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public abstract class DaggerMVPFragment<VIEW extends MVPView, PRESENTER extends MVPPresenter<VIEW>>
        extends MVPFragment<VIEW, PRESENTER> {

    @Inject
    PRESENTER presenter;

    @Override
    protected PRESENTER getPresenter() {
        return presenter;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }
}
