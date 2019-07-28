package com.carapp.carapplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.carapp.carapplication.model.Feedback;

public interface FeedbackRepository  extends JpaRepository<Feedback,Integer> {

}
