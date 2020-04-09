//package cn.rongcloud.live.ui.fragment;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import cn.rongcloud.live.R;
//import cn.rongcloud.live.ui.widget.InputPanel;
//
//public class BottomPanelFragment extends Fragment {
//
//    private static final String TAG = "BottomPanelFragment";
//
//    private InputPanel inputPanel;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_bottombar, container);
//        inputPanel = (InputPanel) view.findViewById(R.id.input_panel);
//        return view;
//    }
//
//    /**
//     * back键或者空白区域点击事件处理
//     *
//     * @return 已处理true, 否则false
//     */
//    public boolean onBackAction() {
//        if (inputPanel.onBackAction()) {
//            return true;
//        }
//        return false;
//    }
//
//    public void setInputPanelVisibility(boolean show) {
//        inputPanel.setVisibility(show ? View.VISIBLE : View.GONE);
//    }
//
//    public void setInputPanelListener(InputPanel.InputPanelListener l) {
//        inputPanel.setPanelListener(l);
//    }
//}
