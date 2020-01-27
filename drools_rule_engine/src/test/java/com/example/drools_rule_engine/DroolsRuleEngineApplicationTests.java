package com.example.drools_rule_engine;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DroolsRuleEngineApplicationTests {

    @Test
    void contextLoads() {
        String verify="规则：package com.xu.drools;\n" +
                "import com.xu.drools.bean.Person;\n" +
                "rule \"2\"\n" +
                "\twhen\n" +
                "        $p : Person(age < 30);\n" +
                "    then\n" +
                "\t\tSystem.out.println(\"hello, young xu2!\");\n" +
                "\t\t$p.setDesc(\"young \"+$p.getName());\n" +
                "\t\tretract($p)\n" +
                "end"+"   数据实体：{\n" +
                "    \"age\":18,\n" +
                "    \"name\":\"xu\",\n" +
                "    \"desc\":\"帅\"\n" +
                "}";
        System.out.println(verify);
        System.out.println("=====================================");
        String rule="package com.xu.drools;\n" +
                "import com.xu.drools.bean.Person;\n" +
                "rule \"2\"\n" +
                "\twhen\n" +
                "        $p : Person(age < 30);\n" +
                "    then\n" +
                "\t\tSystem.out.println(\"hello, young xu2!\");\n" +
                "\t\t$p.setDesc(\"young \"+$p.getName());\n" +
                "\t\tretract($p)\n" +
                "end"+"   数据实体：{\n" +
                "    \"age\":18,\n" +
                "    \"name\":\"xu\",\n" +
                "    \"desc\":\"帅\"\n" +
                "}";

    }

}
