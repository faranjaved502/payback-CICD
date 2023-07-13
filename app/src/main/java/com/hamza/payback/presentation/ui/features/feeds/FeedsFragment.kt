package com.hamza.payback.presentation.ui.features.feeds

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.hamza.payback.data.feed.domain.Feeds
import com.hamza.payback.databinding.FragmentFeedsBinding
import com.hamza.payback.data.feed.network.response.FeedsDTO
import com.hamza.payback.utils.PaybackDialogManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FeedsFragment : Fragment() {
    lateinit var binding: FragmentFeedsBinding

    private val viewModel: FeedsViewModel by viewModels()

    private val feedsList = ArrayList<Feeds>()

    @Inject
    lateinit var adapter: FeedsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFeedsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        observer()
        viewModel.getFeeds("37730308-3c729bac46db25fd2ff0e71fa", "fruits", "photo", true)
    }

    private fun observer() {
        viewModel.feedList.observe(viewLifecycleOwner) { feeds ->
            binding.rvSuggetion.adapter = adapter
            feedsList.clear()
            feeds.hits?.let { feedsList.addAll(it) }
            adapter.setFeedsList(feedsList)
        }

        viewModel.showLoader.observe(viewLifecycleOwner) {
            if (it) {
               PaybackDialogManager.show(requireActivity())
            } else {
                PaybackDialogManager.dismiss()
            }
        }
    }

//    private fun showDeleteDialog(pos: Int){
//        val message =  getString(R.string.remove_user)
//        val heading = getString(R.string.delete_user_heading)
//
//        val builder: androidx.appcompat.app.AlertDialog.Builder? = activity?.let {
//            androidx.appcompat.app.AlertDialog.Builder(it)
//        }
//        builder?.setMessage(message)
//            ?.setTitle(heading)
//        builder?.apply {
//            setPositiveButton(R.string.delete)
//            { dialog, id ->
//
//                viewModel.deleteUsersAccess(allUsers[pos].id!!)
//                adapter.listItems.removeAt(pos)
//                adapter.notifyDataSetChanged()
//                fileFragmentObserver.createAction()
//                dialog.dismiss()
//            }
//            setNegativeButton(R.string.cancel)
//            { dialog, id ->
//                dialog.dismiss()
//            }
//        }
//        val dialog = builder?.create()
//        dialog?.show()
//        dialog?.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE)?.setAllCaps(false);
//    }

}