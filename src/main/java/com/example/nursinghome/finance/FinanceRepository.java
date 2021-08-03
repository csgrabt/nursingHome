package com.example.nursinghome.finance;

import com.example.nursinghome.finance.Finance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceRepository extends JpaRepository<Finance, Long> {
}
