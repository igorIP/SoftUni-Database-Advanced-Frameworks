package entities.label;

import entities.label.basic.BasicLabel;

import javax.persistence.Entity;

@Entity
public class ClassicLabel1 extends BasicLabel {

    public ClassicLabel1() {
    }

    public ClassicLabel1(String title, String subtitle) {
        super(title, subtitle);
    }


}
