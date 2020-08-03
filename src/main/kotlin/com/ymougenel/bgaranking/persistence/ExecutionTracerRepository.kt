package com.ymougenel.bgaranking.persistence

import com.ymougenel.bgaranking.models.ExecutionTrace
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExecutionTracerRepository : JpaRepository<ExecutionTrace, Long> {
}