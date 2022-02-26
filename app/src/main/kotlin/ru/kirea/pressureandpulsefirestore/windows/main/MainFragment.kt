package ru.kirea.pressureandpulsefirestore.windows.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent
import ru.kirea.pressureandpulsefirestore.data.ItemType
import ru.kirea.pressureandpulsefirestore.databinding.MainFragmentBinding
import ru.kirea.pressureandpulsefirestore.di.Scopes
import ru.kirea.pressureandpulsefirestore.windows.AlertDialogAdd
import ru.kirea.pressureandpulsefirestore.windows.main.adapter.BackgroundItemDecorator
import ru.kirea.pressureandpulsefirestore.windows.main.adapter.MainAdapter
import ru.kirea.pressureandpulsefirestore.windows.main.adapter.MainDiffUtil

class MainFragment: Fragment() {

    private val scope = KoinJavaComponent.getKoin().createScope<MainFragment>()
    private val viewModel: MainViewModel = scope.get(qualifier = named(Scopes.MAIN_VIEW_MODEL))
    private val mainAdapter = MainAdapter()

    private var _binding: MainFragmentBinding? = null
    private val binding
        get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        initListener()
        initRecyclerView()
        viewModel.loadData()
        return binding?.root
    }

    /** Инициализация отображения списка */
    private fun initRecyclerView() {
        binding?.let {
            it.recyclerview.adapter = mainAdapter
            it.recyclerview.layoutManager = LinearLayoutManager(requireContext())
            it.recyclerview.itemAnimator = DefaultItemAnimator()
            it.recyclerview.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            it.recyclerview.addItemDecoration(BackgroundItemDecorator())
        }
    }

    /** Инициализация слушателей нажатия на кнопки */
    private fun initListener() {
        binding?.fab?.setOnClickListener {
            AlertDialogAdd.createDialog(requireContext()) { viewModel.saveData(it) }
                .show()
        }
    }

    /**
     * Обработать событие от viewModel.
     * @param state полученное состояние.
     */
    private fun renderData(state: MainState) {
        when(state) {
            is MainState.Loading -> binding?.progressBar?.isVisible = true

            //получен список данных
            is MainState.Success -> setData(state.appDataList)
        }
    }

    /** Обновить данные в списке */
    private fun setData(appDataList: MutableList<ItemType>) {
        val diffUtilResult = DiffUtil.calculateDiff(MainDiffUtil(mainAdapter.items, appDataList))
        mainAdapter.items = appDataList
        diffUtilResult.dispatchUpdatesTo(mainAdapter)
        binding?.progressBar?.isVisible = false
    }
}