package br.com.tecapp.mavelapp.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BasePresenterImp<V : BaseView>(var view: V?) : BasePresenter {

    private var disposables: CompositeDisposable? = null

    init {
        disposables?.let {
            if (it.isDisposed) {
                disposables = CompositeDisposable()
            }
        }
    }

    override fun detachView() {
        view = null
        disposables?.let {
            it.clear()
            it.dispose()
        }
    }

    override fun addDisposable(disposable: Disposable) {
        disposables?.add(disposable)
    }

    fun getError(throwable: Throwable) {
        throwable.printStackTrace()
    }
}