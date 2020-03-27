package br.com.tecapp.mavelapp.ui.base

import io.reactivex.disposables.Disposable

interface BasePresenter {
    fun detachView()
    fun addDisposable(disposable: Disposable)
}