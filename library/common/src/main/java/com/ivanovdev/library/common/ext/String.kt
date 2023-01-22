package com.ivanovdev.library.common.ext

fun String.checkDouble(): Boolean =
    if (this == ".") {
        false
    } else if (this.substringAfter(".").contains(".")) {
        false
//    } else if (this.substringAfter(".").length > 2) {
//        false
    } else
        true
