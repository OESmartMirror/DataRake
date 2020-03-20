package com.datarake.utils;

public class DataLine
{
    //referencia
    private String user;
    private String emotion;
    private Integer emotionPrecent;
    private String userEmotionPair;
    //eval
    private String evalUser;
    private String evalEmotion;
    private Integer evalEmotionPrecent;
    private String evalUserEmotionPair;

    private Double distance;

    public DataLine(String _user, String _picName, String _distance)
    {
        if(_user.split("_").length == 2)
        {
            this.user = _user.split("_")[0];
            this.emotion = _user.split("_")[1];
            this.emotionPrecent = null;
            this.userEmotionPair = _user.toLowerCase();
        }
        else if(_user.split("_").length == 3)
        {
            this.user = _user.split("_")[0];
            this.emotion = _user.split("_")[1];
            this.emotionPrecent = Integer.valueOf(_user.split("_")[2]);
            this.userEmotionPair = this.user.concat("_").concat(this.emotion);
        }

        this.evalUser = _picName.split("_")[0];
        this.evalEmotion = _picName.split("_")[1];
        //this.evalUserEmotionPair = this.evalUser.concat("_").concat(this.evalEmotion).toLowerCase();
        this.evalUserEmotionPair = _picName.substring(0,3).toUpperCase();
        this.evalEmotionPrecent = Integer.valueOf(_picName.split("_")[2].split("\\.")[0]);
        this.distance = Double.valueOf(_distance);
    }

    public String getUser()
    {
        return this.user;
    }

    public String getEvalUser()
    {
        return this.evalUser;
    }
    public String getUserEmotionPair()
    {
        return this.userEmotionPair;
    }

    public  String getEvalUserEmotionPair()
    {
        return this.evalUserEmotionPair;
    }

    public Double getDistance()
    {
        return this.distance;
    }

    public Boolean isMatchingUser()
    {
        return this.user.toLowerCase().equals(this.evalUser.toLowerCase());
    }

    public Boolean isMatchingEmotion()
    {
        return this.emotion.toLowerCase().equals(this.evalEmotion.toLowerCase());
    }

    public Boolean isMatching()
    {
        return this.isMatchingUser() && this.isMatchingEmotion();
    }
    @Override
    public String toString()
    {
        if(null == this.emotionPrecent)
        {
            return new StringBuffer(this.userEmotionPair.toUpperCase()).append(';')
                    .append(this.evalUserEmotionPair.toUpperCase()).append('_').append(this.evalEmotionPrecent).append(';')
                    .append(this.distance).toString();
        }
        else
        {
            return new StringBuffer(this.userEmotionPair.toUpperCase()).append('_').append(this.emotionPrecent).append(';')
                    .append(this.evalUserEmotionPair.toUpperCase()).append('_').append(this.evalEmotionPrecent).append(';')
                    .append(this.distance).toString();
        }

    }
}
