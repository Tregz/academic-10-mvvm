package com.tregz.miksing.data.user

import com.tregz.miksing.data.DataModel

val DataModel.User.id
    get() = _key
val DataModel.User.createdAt
    get() = copy
val DataModel.User.email
    get() = auth