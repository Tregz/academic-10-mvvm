package com.tregz.miksing.data

val DataModel.createdAt
    get() = copy

var DataModel.id
    get() = _key
    set(value) { _key = value }