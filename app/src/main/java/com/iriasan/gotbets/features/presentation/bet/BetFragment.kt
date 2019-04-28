package com.iriasan.gotbets.features.presentation.bet

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.iriasan.gotbets.BaseFragment
import com.iriasan.gotbets.R
import com.iriasan.gotbets.features.domain.models.BetModel
import kotlinx.android.synthetic.main.fragment_bet.*


/**
 * Created by Iria Sanchez on 28/04/2019.
 */
class BetFragment : BaseFragment() {

   lateinit var listCharacters: MutableList<BetModel>

    override fun layoutId(): Int = R.layout.fragment_bet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        listCharacters = getCharacters()
        rvBets.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        rvBets.layoutManager = LinearLayoutManager(context)
        rvBets.adapter = BetsAdapter(listCharacters) { betModel: BetModel, position: Int ->
            listCharacters[position].stateId = betModel.stateId

        }
    }

    override fun showProgress() {
        progress_bet_fragment.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bet_fragment.visibility = View.GONE
    }

    companion object {
        fun newInstance(): BetFragment {
            return BetFragment()
        }
    }

    private fun getCharacters(): MutableList<BetModel>{

        var listCharacters: MutableList<BetModel> = mutableListOf()
        listCharacters.add(BetModel(getString(R.string.name_cersei), 0, 0))
        listCharacters.add(BetModel(getString(R.string.name_jaime), 1, 0))
        listCharacters.add(BetModel(getString(R.string.name_tyrion), 2, 0))
        listCharacters.add(BetModel(getString(R.string.name_daenerys), 3, 0))
        listCharacters.add(BetModel(getString(R.string.name_jon), 4, 0))
        listCharacters.add(BetModel(getString(R.string.name_arya), 5, 0))
        listCharacters.add(BetModel(getString(R.string.name_sansa), 6, 0))
        listCharacters.add(BetModel(getString(R.string.name_bran), 7, 0))
        listCharacters.add(BetModel(getString(R.string.name_gendry), 8, 0))
        listCharacters.add(BetModel(getString(R.string.name_samwell), 9, 0))
        listCharacters.add(BetModel(getString(R.string.name_jorah), 10, 0))
        listCharacters.add(BetModel(getString(R.string.name_the_dog), 11, 0))
        listCharacters.add(BetModel(getString(R.string.name_the_mountain), 12, 0))
        listCharacters.add(BetModel(getString(R.string.name_brienne), 13, 0))
        listCharacters.add(BetModel(getString(R.string.name_theon), 14, 0))
        listCharacters.add(BetModel(getString(R.string.name_varys), 15, 0))

        return  listCharacters
    }
}