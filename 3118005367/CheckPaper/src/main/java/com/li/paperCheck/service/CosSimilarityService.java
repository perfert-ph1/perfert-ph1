package com.li.paperCheck.service;

import java.io.IOException;

public interface CosSimilarityService {
    double getSimilarity(String context,String copyContext);
}
