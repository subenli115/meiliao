package com.ziran.meiliao.ui.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ziran.meiliao.R;
import com.ziran.meiliao.widget.NewExpandableTextView;

public class Test2Activity extends AppCompatActivity {

    private com.ziran.meiliao.widget.NewExpandableTextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        this.tv = (NewExpandableTextView) findViewById(R.id.tv);
        tv.setText("新华社金边1月10日电（记者刘劼尚军）国务院总理李克强当地时间1月10日下午在金边和平大厦出席澜沧江－湄公河合作第二次领导人会议。李克强和柬埔寨首相洪森共同主持会议。老挝总理通伦、泰国总理巴育、越南总理阮春福和缅甸副总统吴敏瑞出席。\n" + "\n" + "　　李克强表示，澜湄合作是首个由流域六国共同创建的新型次区域合作机制，是共商共建“一带一路”的重要平台。两年来，我们秉持发展为先、平等协商、务实高效、开放包容的合作理念，取得了超出预期的成效，形成了“领导人引领、全方位覆盖、各部门参与”的澜湄格局，创造了“天天有进展、月月有成果、年年上台阶”的澜湄速度，培育了“平等相待、真诚互助、亲如一家”的澜湄文化。\n" + "\n" + "　　李克强指出，澜湄合作已成为本地区最具活力、最富成果的合作机制之一。中方愿与湄公河国家一道，打造澜湄流域经济发展带，建设澜湄国家命运共同体。李克强就推动澜湄合作从培育期顺利迈向成长期，打造次区域和南南合作典范提出以下建议：\n" + "\n" + "　　第一，做好水资源合作。要相互信任、相互理解、相互支持，加强上下游协作，照顾彼此关切，统筹处理好经济发展和生态保护的关系。着眼于水资源可持续利用，制定“水资源合作五年行动计划”，加强旱涝灾害应急管理，开展水资源和气候变化影响等联合研究，改进水质监测系统，打造共建共享的水资源合作平台。");
    }
}
