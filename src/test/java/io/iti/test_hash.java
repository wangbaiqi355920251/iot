package io.iti;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test_hash {

//注意要和main包中的路径一样才不会报错



        @Test
        public void test() {

           String a = new Sha256Hash("123", "2qvY8SqZRS9kgKzYewn1").toHex();
            System.out.println(a);
        }

    }


