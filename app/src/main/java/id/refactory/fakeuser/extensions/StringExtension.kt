package id.refactory.fakeuser.extensions

import android.util.Patterns

fun String.isEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isName(): Boolean {
    return split(" ").size > 1 && length >= 8
}

fun String.isPassword(): Boolean {
    return length >= 8
}